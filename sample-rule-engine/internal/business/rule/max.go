package rule

import (
	disc "github.com/herbert/sample-rule-engine/internal/business/discount/model"
	prod "github.com/herbert/sample-rule-engine/internal/business/product/model"
	user "github.com/herbert/sample-rule-engine/internal/business/user/model"
)

type Max struct {
	Next Rule
}

func (m *Max) Execute(u *user.User, p *prod.Product, d *disc.Discount) {
	if d.Percentage > 10.0 {
		d.Percentage = 10.0
	}
}

func (m *Max) SetNext(nextRule Rule) {
	m.Next = nextRule
}
