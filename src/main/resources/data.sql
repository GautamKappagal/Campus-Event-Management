-- Insert sample Clubs
INSERT INTO club (name, description) VALUES ('Tech Club', 'All about coding and hackathons');
INSERT INTO club (name, description) VALUES ('Music Club', 'Jam sessions and music events');

-- Insert sample Users
INSERT INTO app_user (username, password, role) VALUES ('student', 'student', 'STUDENT');
INSERT INTO app_user (username, password, role) VALUES ('admin', 'admin', 'ADMIN');
INSERT INTO app_user (username, password, role) VALUES ('clubadmin', 'clubadmin', 'CLUB_ADMIN');

-- Insert sample Event (unapproved by default)
INSERT INTO event (title, description, capacity, approved, club_id)
VALUES ('Spring Boot Workshop', 'Learn to build APIs', 50, FALSE, 1);

INSERT INTO event (title, description, capacity, approved, club_id)
VALUES ('Music Night', 'Live performance event', 100, FALSE, 2);