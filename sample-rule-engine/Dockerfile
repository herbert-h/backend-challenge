FROM golang:1.16-alpine3.13 as builder
WORKDIR /src
ADD . /src
RUN go mod download
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -ldflags="-s -w" -o app cmd/main.go

FROM alpine:3.13
RUN apk --no-cache add ca-certificates
WORKDIR /root/
COPY --from=builder /src/app .
COPY --from=builder /src/configs/* configs/

CMD ["./app"]
