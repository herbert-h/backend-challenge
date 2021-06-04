package user

import (
	"testing"

	"github.com/herbert/sample-rule-engine/internal/business/user/model"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
)

func TestShouldReturnNilWhenUserIdIsEmpty(t *testing.T) {
	userId := ""
	repository := new(mockedUserRepository)

	svc := &ServiceImp{
		UserRepository: repository,
	}

	user := svc.GetUserById(userId)

	assert.Nil(t, user)
}

func TestShouldReturnNilWhenUserIdIsNotUUID(t *testing.T) {
	userId := "13130819082101"
	repository := new(mockedUserRepository)

	svc := &ServiceImp{
		UserRepository: repository,
	}

	user := svc.GetUserById(userId)

	assert.Nil(t, user)
}

func TestShouldReturnUserWhenUserIdIsUUID(t *testing.T) {
	userId := "44d7b183-853d-4735-becd-f76127e3ac78"
	repository := new(mockedUserRepository)

	repository.On("GetById", userId).Return(&model.User{Id: userId, FirstName: "Herbert"}, nil)

	svc := &ServiceImp{
		UserRepository: repository,
	}

	user := svc.GetUserById(userId)

	assert.Equal(t, userId, user.Id)
	assert.Equal(t, "Herbert", user.FirstName)
}

type mockedUserRepository struct{ mock.Mock }

func (c *mockedUserRepository) GetById(userId string) (*model.User, error) {
	args := c.Called(userId)
	return args.Get(0).(*model.User), args.Error(1)
}
