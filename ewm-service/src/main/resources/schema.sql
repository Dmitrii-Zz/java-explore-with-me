DROP TABLE IF EXISTS categories, locations, users, events, requests, compilations, compilation_events CASCADE;

CREATE TABLE IF NOT EXISTS categories (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    CONSTRAINT uq_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS locations (
    id BIGINT GENERATION BY DEFAULT AS IDENTITY PRIMARY KEY,
    lat NUMERIC,
    lon NUMERIC
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATION BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(254) NOT NULL,
    name VARCHAR(250) NOT NULL,
    CONSTRAINT uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS events (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    annotation VARCHAR(2000) NOT NULL,
    category INTEGER NOT NULL REFERENCES categories (id) ON DELETE CASCADE,
    description VARCHAR(7000) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    event_date TIMESTAMP NOT NULL,
    published_on TIMESTAMP,
    location BIGINT NOT NULL REFERENCES locations (id) ON DELETE CASCADE,
    paid BOOLEAN DEFAULT 'false',
    participant_limit INTEGER DEFAULT '0',
    confirmed_requests INTEGER,
    initiator BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    request_moderation BOOLEAN DEFAULT 'true',
    title VARCHAR(120) NOT NULL,
    state VARCHAR,
    views BIGINT
);

CREATE TABLE IF NOT EXISTS requests (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    event BIGINT NOT NULL REFERENCES events (id) ON DELETE CASCADE,
    requester BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status VARCHAR NOT NULL
);

CREATE INDEX index_id on requests (id);

CREATE TABLE IF NOT EXISTS compilations (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    pinned BOOLEAN DEFAULT 'false',
    title VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS compilation_events (
    comp_id BIGINT NOT NULL REFERENCES compilations (id) ON DELETE CASCADE,
    event_id BIGINT NOT NULL REFERENCES events (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text VARCHAR(1000) NOT NULL,
    created TIMESTAMP NOT NULL,
    author BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    event_id BIGINT NOT NULL REFERENCES events (id) ON DELETE CASCADE,
    likes BIGINT DEFAULT 0
);