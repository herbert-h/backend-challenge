package user

type Repository interface {
	GetById(userId string) (*User, error)
}
