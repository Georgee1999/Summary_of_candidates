CREATE TABLE IF NOT EXISTS Candidates (
                                          id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                          first_name VARCHAR(200) NOT NULL,
                                          last_name VARCHAR(200) NOT NULL,
                                          email VARCHAR(200) NOT NULL,
                                          phoneNumber VARCHAR(200) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Skills(
                                        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                        technology VARCHAR(200) NOT NULL,
                                        description VARCHAR(200) NOT NULL
);
CREATE TABLE IF NOT EXISTS Candidate_skill (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               candidate_id INT NOT NULL,
                                               skill_id INT NOT NULL,
                                               rating INT NOT NULL CHECK (rating >= 1 AND rating <= 10),
                                               FOREIGN KEY (candidate_id) REFERENCES Candidates(id),
                                               FOREIGN KEY (skill_id) REFERENCES Skills(id)
);
