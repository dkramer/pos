--TODO make invoice data their own columns?
--could possibly make invoice_id auto incrementing
--customer_id should be foreign key to another table
CREATE TABLE IF NOT EXISTS invoice
(
    customer_id BIGINT NOT NULL,
    invoice_id BIGINT NOT NULL,
    invoice_data CHARACTER VARYING NOT NULL,
    PRIMARY KEY (invoice_id)
);

--index customer_id because that is what we are querying by
CREATE INDEX customer_id ON invoice(customer_id);