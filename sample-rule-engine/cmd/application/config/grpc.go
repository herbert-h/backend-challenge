package config

import "google.golang.org/grpc"

func ConfigureGrpc() *grpc.Server {
	s := grpc.NewServer()

	return s
}
