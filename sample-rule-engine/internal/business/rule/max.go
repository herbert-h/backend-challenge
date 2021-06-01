package rule

import (
	"github.com/herbert/sample-rule-engine/internal/business/discount/model"
	"github.com/herbert/sample-rule-engine/internal/business/product"
	"github.com/herbert/sample-rule-engine/internal/business/user"
)

type Max struct {
	Next Rule
}

func (m *Max) Execute(u *user.User, p *product.Product, d *model.Discount) {
	if d.Percentage > 10.0 {
		d.Percentage = 10.0
	}
}

func (m *Max) SetNext(nextRule Rule) {
	m.Next = nextRule
}
