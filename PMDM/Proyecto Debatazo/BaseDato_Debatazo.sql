CREATE TABLE `user` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL
);

CREATE TABLE `user_profile` (
  `user_id` integer PRIMARY KEY,
  `profile_img` varchar(255) DEFAULT 'https://i.imgur.com/zWlUBeC.png',
  `user_name` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT '***',
  `self` varchar(255),
  `age` integer DEFAULT 0,
  `sex` char(1) DEFAULT '*' COMMENT' H = Hombre, M = Mujer, * = Indefinido'
);

CREATE TABLE `debate` (
  `id` integer AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `title` varchar(255) NOT NULL,
  `like` integer DEFAULT 0,
  `description` varchar(255) NOT NULL,
  `image_url` varchar(255),
  `release_date` date NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `band` (
  `id` integer AUTO_INCREMENT,
  `debate_id` integer NOT NULL,
  `description` varchar(255) NOT NULL,
  `num` integer DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `comment` (
  `id` integer AUTO_INCREMENT,
  `debate_id` integer NOT NULL,
  `from_user` integer NOT NULL,
  `pid` integer NULL default 0,
  `band_id` integer,
  `description` varchar(255) NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`Id`)
);

ALTER TABLE `user_profile` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `debate` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `band` ADD FOREIGN KEY (`debate_id`) REFERENCES `debate` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`debate_id`) REFERENCES `debate` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`from_user`) REFERENCES `user` (`id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`band_id`) REFERENCES `band` (`id`);
