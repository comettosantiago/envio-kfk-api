Example of Event-driven Java microservice that manages shipping using Kafka for async communication between services.

Consumes purchase events from Kafka, creates shipping entities, and produces new events for the main service.
- Listens to events on the `compra.creada` topic.
- Persists the related shipping entity.
- Publishes a `envio.generado` event to Kafka, which is consumed by the main API.

## Running the project

### Prerequisites
- Main API (https://github.com/comettosantiago/compra-kfk-api)

### Running
1. Kafka broker and database must be running (instructions in the main API).
2. Run the application from your IDE or with Maven
