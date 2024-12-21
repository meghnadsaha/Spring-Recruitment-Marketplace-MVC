SELECT * FROM unihyr.userrole;


UPDATE unihyr.userrole
SET userrole = 'ROLE_CON_USER'
WHERE userid = 'cons_user@example.com';



    
use unihyr;
ALTER TABLE `postconsultant`
  MODIFY `postId` bigint NOT NULL DEFAULT 0,  -- Set a default value for postId
  MODIFY `post_id` bigint NOT NULL DEFAULT 0;  -- Set a default value for post_id

SHOW ENGINE INNODB STATUS;
use unihyr;
CREATE INDEX idx_postId ON postconsultant (postId);
CREATE INDEX idx_post_id ON postconsultant (post_id);

