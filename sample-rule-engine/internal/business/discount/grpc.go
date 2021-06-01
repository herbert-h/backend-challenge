package discount

import (
	"context"
	"log"

	pb "github.com/herbert/sample-rule-engine/proto"
	"github.com/jmoiron/sqlx"
	"google.golang.org/grpc"
)

type GrpcServer struct {
	s   *grpc.Server
	svc Service
}

func NewGrpcServer(s *grpc.Server, db *sqlx.DB) *GrpcServer {
	return &GrpcServer{
		s:   s,
		svc: NewService(db),
	}
}

func (svr *GrpcServer) RegisterServer() {
	pb.RegisterDiscountRuleEngineServer(svr.s, &DiscountRuleEngineGrpcServer{s: svr})
}

type DiscountRuleEngineGrpcServer struct {
	s *GrpcServer
	pb.UnimplementedDiscountRuleEngineServer
}

func (gs *DiscountRuleEngineGrpcServer) CalculateDiscount(ctx context.Context, in *pb.DiscountRequest) (*pb.DiscountReply, error) {
	productId := in.GetProductId()
	userId := in.GetUserId()
	log.Printf("Received: product=%v & user=%v", productId, userId)
	p, err := gs.s.svc.Apply(productId, userId)
	if err != nil {
		return nil, err
	}
	return &pb.DiscountReply{Percentage: p}, nil
}
