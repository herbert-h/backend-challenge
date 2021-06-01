package health

type Service interface {
	GetStatus() Health
}

type ServiceImp struct{}

func NewService() *ServiceImp {
	return &ServiceImp{}
}

func (svc ServiceImp) GetStatus() Health {
	return Health{Status: "OK"}
}
