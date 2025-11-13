"""
Employee data models for the technology company.
"""

from dataclasses import dataclass
from typing import Optional


@dataclass
class Employee:
    """Represents an employee in the technology company."""
    code: str
    name: str
    position: str
    department: str
    salary: float
    email: str
    
    def __str__(self) -> str:
        return (f"Employee Code: {self.code}\n"
                f"Name: {self.name}\n"
                f"Position: {self.position}\n"
                f"Department: {self.department}\n"
                f"Salary: ${self.salary:,.2f}\n"
                f"Email: {self.email}")

