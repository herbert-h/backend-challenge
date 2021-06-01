package rule

type Service interface {
	BuildRuleChain() Rule
}

type ServiceImp struct{}

func NewService() *ServiceImp {
	return &ServiceImp{}
}

// UPGRADE: Make ChainRule a Property to avoid create chain on every rpc call
func (svc ServiceImp) BuildRuleChain() Rule {

	max := &Max{}
	blackfriday := &BlackFriday{Next: max}
	birthday := &Birthday{Next: blackfriday}

	return birthday
	// TODO
	// I can add priority on each rule
	// put all on list, sort by rule priority
	// and auto generate chain (just create chain and be happy)
	// example:
	// - max_rule aways will be the last rule priority 0)
	// - birth and blackfriday can have same priority (greater 0), order does not effect anything
}
