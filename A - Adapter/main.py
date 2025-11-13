"""
Main application that demonstrates the Adapter pattern.
Extracts employee code from command line: python main.py <employee_code>
"""

import sys
from typing import Optional
from employee_models import Employee
from database_service import DatabaseEmployeeService
from rest_service import RESTEmployeeService
from employee_adapter import DatabaseEmployeeAdapter, RESTEmployeeAdapter


def get_employee_via_database(employee_code: str) -> Optional[Employee]:
    """
    Query employee using the database service through adapter.
    
    Args:
        employee_code: The employee code to search for
        
    Returns:
        Employee object if found, None otherwise
    """
    print("\n" + "="*60)
    print("Querying via DATABASE SERVICE")
    print("="*60)
    
    database_service = DatabaseEmployeeService()
    adapter = DatabaseEmployeeAdapter(database_service)
    return adapter.get_employee(employee_code)


def get_employee_via_rest(employee_code: str) -> Optional[Employee]:
    """
    Query employee using the REST service through adapter.
    
    Args:
        employee_code: The employee code to search for
        
    Returns:
        Employee object if found, None otherwise
    """
    print("\n" + "="*60)
    print("Querying via REST WEB SERVICE")
    print("="*60)
    
    rest_service = RESTEmployeeService()
    adapter = RESTEmployeeAdapter(rest_service)
    return adapter.get_employee(employee_code)


def main():
    """Main function that processes command line arguments and queries employee data."""
    # Extract employee code from command line arguments
    if len(sys.argv) < 2:
        print("Usage: python main.py <employee_code>")
        print("Example: python main.py EMP001")
        sys.exit(1)
    
    employee_code = sys.argv[1].upper()
    
    print(f"\nSearching for employee with code: {employee_code}")
    
    # Query using database service (through adapter)
    employee_db = get_employee_via_database(employee_code)
    
    if employee_db:
        print("\n✓ Employee found via Database Service:")
        print(employee_db)
    else:
        print(f"\n✗ Employee with code '{employee_code}' not found in database.")
    
    # Query using REST service (through adapter)
    employee_rest = get_employee_via_rest(employee_code)
    
    if employee_rest:
        print("\n✓ Employee found via REST Service:")
        print(employee_rest)
    else:
        print(f"\n✗ Employee with code '{employee_code}' not found via REST API.")
    
    print("\n" + "="*60)
    print("Both services use different method signatures but are")
    print("made compatible through the Adapter pattern!")
    print("="*60 + "\n")


if __name__ == "__main__":
    main()

