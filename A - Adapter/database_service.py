"""
Database service for querying employee data directly from the database.
"""

import os
import psycopg2
from psycopg2.extras import RealDictCursor
from typing import Optional
from employee_models import Employee


class DatabaseEmployeeService:
    """
    Service that queries employee data directly from the database.
    Uses method signature: get_employee_by_id(employee_id: str)
    """
    
    def __init__(self):
        """Initialize the database service with PostgreSQL connection."""
        # Database connection parameters
        self._db_config = {
            'host': 'localhost',
            'port': 5432,
            'dbname': 'postgres',
            'user': 'alejo190404',
            'password': 'super-password-4'
        }
        self._connection = None
    
    def _get_connection(self):
        """Get or create a database connection."""
        if self._connection is None or self._connection.closed:
            try:
                self._connection = psycopg2.connect(**self._db_config)
            except psycopg2.Error as e:
                print(f"[Database Service] Error connecting to database: {e}")
                raise
        return self._connection
    
    def _close_connection(self):
        """Close the database connection."""
        if self._connection and not self._connection.closed:
            self._connection.close()
            self._connection = None
    
    def get_employee_by_id(self, employee_id: str) -> Optional[Employee]:
        """
        Query employee data from the database using employee ID.
        
        Args:
            employee_id: The employee code/ID to search for
            
        Returns:
            Employee object if found, None otherwise
        """
        print(f"[Database Service] Querying database for employee ID: {employee_id}")
        
        try:
            conn = self._get_connection()
            cursor = conn.cursor(cursor_factory=RealDictCursor)
            
            # Query employee by code
            # Assuming table name is 'employees' with columns matching Employee model
            query = """
                SELECT code, name, position, department, salary, email
                FROM Empleados
                WHERE code = %s
            """
            
            cursor.execute(query, (employee_id,))
            row = cursor.fetchone()
            
            cursor.close()
            
            if row:
                return Employee(
                    code=row['code'],
                    name=row['name'],
                    position=row['position'],
                    department=row['department'],
                    salary=float(row['salary']),
                    email=row['email']
                )
            else:
                return None
                
        except psycopg2.Error as e:
            print(f"[Database Service] Database error: {e}")
            return None
        except Exception as e:
            print(f"[Database Service] Unexpected error: {e}")
            return None
    
    def __del__(self):
        """Clean up database connection on object destruction."""
        self._close_connection()

