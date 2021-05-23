CREATE TABLE 'tour_shop'.'tours' (
  'id' INT NOT NULL AUTO_INCREMENT,
  'title' VARCHAR(100) NOT NULL,
  'description' VARCHAR(255) NOT NULL,
  'route' INT NOT NULL,
  'cost' INT NOT NULL,
  'notice' VARCHAR(255) NULL,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'title_UNIQUE' ('title' ASC) VISIBLE);