package com.rev.jdbc.model;

/**
 * Represents one row in the employees table.
 */
public record Employee(int id, String name, int salary) {
}
