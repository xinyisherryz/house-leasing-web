
-- Project 

/*
 * set up
 */
set global local_infile=1;
drop database if exists housesystem;
create database if not exists housesystem;
use housesystem;


/*
 * drop tables
 */
drop table if exists creditCards;
drop table if exists favorites;
drop table if exists leaseHistories;
drop table if exists dealsRatings;
drop table if exists houses;
drop table if exists dealers;
drop table if exists owners;
drop table if exists buyers;
drop table if exists admins;
drop table if exists users;
drop table if exists companies;



/*
 * users table
 */
create table if not exists users (
    userId integer auto_increment,
    firstName varchar(255),
    lastName varchar(255),
    phone varchar(255),
    email varchar(255),
    gender varchar(255),
    age integer,
    constraint pk_users_userid primary key (userId)
);


/*
 * admins Table
 */

create table if not exists admins (
    adminId integer,
    userId integer,
    constraint pk_admins_adminId primary key (adminId),
    constraint fk_admins_userId foreign key (userId) references users(userId) on update cascade on delete cascade
);



/*
 * owners table 
 */
create table if not exists owners (
    userId integer,
    houseId integer,
    constraint pk_owners_userId primary key (userId),
    constraint fk_owners_userId foreign key (userId) references users(userId) on update cascade on delete cascade
);




/*
 * buyers table 
 */
create table if not exists buyers (
    userId integer,
    constraint pk_buyers_userId primary key (userId),
    constraint fk_buyers_userId foreign key (userId) references users(userId) on update cascade on delete cascade
);





/*
 * creditCards table 
 */
create table if not exists creditCards(
    cardNumber varchar(16),
    firstName varchar(255),
    lastName varchar(255),
    expYear integer,
    expMonth integer,
    buyerId integer,
    constraint pk_creditCards_cardNumber primary key (cardNumber),
    constraint fk_creditCards_buyerId foreign key (buyerId) references buyers(userId) on update cascade on delete cascade
);


/*
 * companies table 
 */
create table if not exists companies (
    companyId integer auto_increment,
    companyName varchar(255),
    foundedTime integer,
    founder varchar(255),
    companySize varchar(255),
    ranking integer,
    constraint pk_companies_companyId primary key (companyId)
);


/*
 * dealers table 
 */
create table if not exists dealers (
	userId integer,
    companyId integer,
    yearsOfExperience integer,
    constraint pk_dealers_userId primary key (userId),
    constraint fk_dealers_userId foreign key (userId) references users(userId) on update cascade on delete cascade,
    constraint fk_dealers_companyId foreign key (companyId) references companies(companyId) on update cascade on delete set null
);


/*
 * houses table 
 */
create table if not exists houses (
    houseId integer auto_increment,
    url varchar(255),
    region varchar(255),
    regionUrl varchar(255),
    price integer,
    houseType enum ('apartment', 'condo', 'assistedLiving', 'cottageCabin', 'house', 'duplex', 'flat', 'inLaw', 'loft', 'land', 'manufactured', 'townhouse'),
    sqFeet integer,
    beds integer,
    baths decimal(2,1),
    catsAllowed boolean,
    dogsAllowed boolean,
    smokingAllowed boolean,
    wheelchairAccess boolean,
    electricVehicleCharge boolean,
    comesFurnished boolean,
    laundryOption enum ('laundryInBuilding', 'laundryOnSite', 'noLaundryOnSite', 'washerDryerHookups', 'washerDryerInUnit'),
    parkingOption enum ('attachedGarage', 'carport', 'detachedGarage', 'noParking', 'offStreetParking', 'streetParking', 'valetParking'),
    imgUrl varchar(255),
    description text,
    lat decimal(4,2),
    lon decimal(5,2),
    state char(2),
    constraint pk_houses_houseId primary key (houseId)
);


/*
 * favorites table 
 */
create table if not exists favorites (
    favoriteId integer auto_increment,
    houseId integer,
    buyerId integer,
    constraint pk_favorites_favoriteId primary key (favoriteId),
    constraint fk_favorites_houseId foreign key (houseId) references houses(houseId) on update cascade on delete set null,
    constraint fk_favorites_buyerId foreign key (buyerId) references buyers(userId) on update cascade on delete set null
);


/*
 * leaseHistories table 
 */
create table if not exists leaseHistories (
    leaseId integer auto_increment,
    houseId integer,
    ownerId integer,
    dealerId integer,
    buyerId integer,
    price decimal,
    constraint pk_leaseHistories_leaseId primary key (leaseId),
    constraint fk_leaseHistories_houseId foreign key (houseId) references houses(houseId) on update cascade on delete set null,
    constraint fk_leaseHistories_ownerId foreign key (ownerId) references owners(userId) on update cascade on delete set null,
    constraint fk_leaseHistories_dealerId foreign key (dealerId) references dealers(userId) on update cascade on delete set null,
    constraint fk_leaseHistories_buyerId foreign key (buyerId) references buyers(userId) on update cascade on delete set null
);


/*
 * dealsRatings table 
 */

create table if not exists dealsRatings (
    ratingId integer auto_increment,
    houseId integer,
    ownerId integer,
    dealerId integer,
    rating integer,
    constraint pk_dealsRatings primary key (ratingId),
    constraint fk_dealsRatings_houseId foreign key (houseId) references houses(houseId) on update cascade on delete set null,
    constraint fk_dealsRatings_ownerId foreign key (ownerId) references owners(userId) on update cascade on delete set null,
    constraint fk_dealsRatings_dealerId foreign key (dealerId) references dealers(userId) on update cascade on delete set null
);


/*
 * loading data
 */

LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/users.csv' INTO TABLE users
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (userId, firstName, lastName, phone, email, gender,age);
  
  
LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/admin.csv' INTO TABLE admins
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (adminId, userId);
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/owner.csv' INTO TABLE owners
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (userId, houseId);
  
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/buyer.csv' INTO TABLE buyers
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (userId);  
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/credit.csv' INTO TABLE creditCards
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (cardNumber, firstName, lastName, expYear, expMonth, buyerId);
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/company.csv' INTO TABLE companies
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (companyId, companyName, foundedTime, founder, companySize, ranking);
  
LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/dealer.csv' INTO TABLE dealers
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (userId, companyId, yearsOfExperience);
  
  
LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/house.csv' INTO TABLE houses
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (houseId, url, region, regionUrl, price, houseType, sqFeet, beds, baths, catsAllowed, dogsAllowed, smokingAllowed, wheelchairAccess,  electricVehicleCharge ,comesFurnished, laundryOption, parkingOption, imgUrl, description, lat, lon, state);


  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/favorite.csv' INTO TABLE favorites
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (favoriteId, houseId, buyerId);
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/leaseHistories.csv' INTO TABLE leaseHistories
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (leaseId, houseId,ownerId,dealerId, buyerId, price);
  
  LOAD DATA local INFILE '/Users/xinyizhang/eclipse-workspace/pm_house/data/data_20221127/rating.csv' INTO TABLE dealsRatings
  FIELDS TERMINATED BY ',' ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (ratingId, houseId,ownerId,dealerId, rating);
  
  
  
/*
 *  calculate row numbers
 */
  
  with countinfo as (select "UsersCount" , count(*) as count from users union all
select "adminsCount", count(*) as count  from admins union all
select "ownerCount", count(*) as count  from admins union all
select "buyersCount", count(*)  as count  from buyers union all
select  "creditCardsCount", count(*) as count  from creditCards union all
select "companiesCount", count(*)  as count  from companies union all
select "dealersCount", count(*)  as count  from dealers union all
select "housesCount", count(*)  as count  from houses union all
select "favoritesCount", count(*)  as count  from favorites union all
select "leaseHistoriesCount", count(*)  as count  from leaseHistories union all
select "dealsRatingsCount", count(*)  as count  from dealsRatings )

(select "totalCount", sum(count)  as count from countinfo )
union all (select * from countinfo) ;








