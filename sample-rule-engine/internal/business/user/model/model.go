package model

type User struct {
	Id          string `db:"id"`
	FirstName   string `db:"first_name"`
	LastName    string `db:"last_name"`
	DateOfBirth string `db:"date_of_birth"`
	CreatedAt   string `db:"created_at"`
}
