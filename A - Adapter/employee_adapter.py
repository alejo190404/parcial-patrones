"""
Adapter pattern implementation to make DatabaseEmployeeService and 
RESTEmployeeService compatible with a unified interface.
"""

from typing import Optional, Protocol
from employee_models import Employee
from database_service import DatabaseEmployeeService
from rest_service import RESTEmployeeService


class EmployeeService(Protocol):
    """Target interface that defines the expected method signature."""
    
    def get_employee(self, employee_code: str) -> Optional[Employee]:
        """
        Unified method signature for getting employee data.
        
        Args:
            employee_code: The employee code to search for
            
        Returns:
            Employee object if found, None otherwise
        """
        ...


class DatabaseEmployeeAdapter:
    """
    Adapter for DatabaseEmployeeService.
    Adapts get_employee_by_id(employee_id) to get_employee(employee_code).
    """
    
    def __init__(self, database_service: DatabaseEmployeeService):
        """
        Initialize the adapter with a database service instance.
        
        Args:
            database_service: Instance of DatabaseEmployeeService
        """
        self._database_service = database_service
    
    def get_employee(self, employee_code: str) -> Optional[Employee]:
        """
        Adapts the database service method to the unified interface.
        
        Args:
            employee_code: The employee code to search for
            
        Returns:
            Employee object if found, None otherwise
        """
        return self._database_service.get_employee_by_id(employee_code)


class RESTEmployeeAdapter:
    """
    Adapter for RESTEmployeeService.
    Adapts fetch_employee_data(code) -> Dict to get_employee(employee_code) -> Employee.
    """
    
    def __init__(self, rest_service: RESTEmployeeService):
        """
        Initialize the adapter with a REST service instance.
        
        Args:
            rest_service: Instance of RESTEmployeeService
        """
        self._rest_service = rest_service
    
    def get_employee(self, employee_code: str) -> Optional[Employee]:
        """
        Adapts the REST service method to the unified interface.
        Converts the dictionary response to an Employee object.
        
        Args:
            employee_code: The employee code to search for
            
        Returns:
            Employee object if found, None otherwise
        """
        data = self._rest_service.fetch_employee_data(employee_code)
        if data is None:
            return None
        data = data.data[0]
        # Convert dictionary to Employee object
        return Employee(
            code=data["code"],
            name=data["name"],
            position=data["position"],
            department=data["department"],
            salary=data["salary"],
            email=data["email"]
        )

