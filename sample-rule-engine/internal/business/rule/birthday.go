package rule

import (
	"time"

	disc "github.com/herbert/sample-rule-engine/internal/business/discount/model"
	prod "github.com/herbert/sample-rule-engine/internal/business/product/model"
	user "github.com/herbert/sample-rule-engine/internal/business/user/model"
)

type Birthday struct {
	Next Rule
}

func (b *Birthday) Execute(u *user.User, p *prod.Product, d *disc.Discount) {
	if u != nil {
		bday, _ := time.Parse(time.RFC3339, u.DateOfBirth)
		today := time.Now()
		if bday.Day() == today.Day() && bday.Month() == today.Month() {
			d.Percentage += 5.0
		}
	}
	b.Next.Execute(u, p, d)
}

func (b *Birthday) SetNext(nextRule Rule) {
	b.Next = nextRule
}
