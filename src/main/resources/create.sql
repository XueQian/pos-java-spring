CREATE user'qianyan'@'localhost' IDENTIFIED BY 'pos';

DROP DATABASE IF EXISTS pos_java;
CREATE DATABASE pos_java;

GRANT ALL ON pos_java.* TO 'qianyan'@'localhost';

USE pos_java;

CREATE TABLE categories(
  c_id INT PRIMARY KEY AUTO_INCREMENT,
  c_name VARCHAR(40) NOT NULL
);

INSERT categories VALUES(null,'服装鞋包'),(null,'全球美食'),(null,'日常用品');

CREATE TABLE items(
  i_id INT PRIMARY KEY AUTO_INCREMENT,
  i_barcode VARCHAR(30),
  i_name VARCHAR(30),
  i_unit VARCHAR(8),
  i_price DOUBLE,
  i_categoryid int not null,
  CONSTRAINT category_item_fk FOREIGN KEY(i_categoryid) REFERENCES categories(c_id)
);

INSERT items VALUES
  (null,'ITEM000001','女装','套',1000,1),
  (null,'ITEM000002','手提包','个',200,1),
  (null,'ITEM000003','五花肉','斤',30.5,2),
  (null,'ITEM000004','羊肉泡馍','碗',15,2),
  (null,'ITEM000005','拖把','个',30,3),
  (null,'ITEM000006','鞋架','个',100,3);

CREATE TABLE promotions(
  p_id INT PRIMARY KEY AUTO_INCREMENT,
  p_type INT NOT NULL,
  p_description VARCHAR(200)
);

INSERT promotions VALUES
  (null,1,'买二赠一'),
  (null,2,'第二件半价'),
  (null,3,'打折');

CREATE TABLE items_promotions(
  itemid INT NOT NULL REFERENCES items(i_id),
  promotionid INT NOT NULL REFERENCES promotions(p_id),
  discount DOUBLE,
  PRIMARY KEY(itemid,promotionid)
);

INSERT items_promotions VALUES
  (1,3,90),
  (1,2,null),
  (3,3,80),
  (4,1,null),
  (5,3,85),
  (5,1,null),
  (5,2,null),
  (6,1,null),
  (6,2,null);







