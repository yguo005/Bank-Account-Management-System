# Bank Account Management System

A Java-based banking solution implementing polymorphism, interfaces, and abstract classes to manage different types of bank accounts with varying fee structures and transaction limits.

## Overview

This project demonstrates object-oriented design principles by implementing a banking system with two types of accounts: Savings and Checking. The system uses interfaces for common behavior contracts and abstract classes for code reuse while supporting different account-specific rules.

## Features

- Interface-based design for polymorphic account management
- Abstract base class for shared functionality
- Savings account with withdrawal transaction limits
- Checking account with minimum balance requirements
- Monthly maintenance fee calculations
- Comprehensive input validation and error handling
- Full unit test coverage with JUnit 5

## Project Structure

```
src/bank/
├── IAccount.java          # Account interface contract
├── Account.java           # Abstract base class
├── SavingsAccount.java    # Savings account implementation
└── CheckingAccount.java   # Checking account implementation

test/bank/
├── AccountTest.java       # Abstract class unit tests
├── SavingsAccountTest.java    # Savings account tests
└── CheckingAccountTest.java   # Checking account tests
```

## Architecture

### Interface Design

The system uses the `IAccount` interface to establish a common contract for all account types:

```java
public interface IAccount {
    void deposit(double amount);
    boolean withdraw(double amount);
    double getBalance();
    void performMonthlyMaintenance();
}
```

### Class Hierarchy

```
IAccount (interface)
    ↑
Account (abstract class)
    ↑
    ├── SavingsAccount
    └── CheckingAccount
```

## Account Types

### Savings Account

**Features:**
- Unlimited deposits per month
- 6 penalty-free withdrawals per month
- Transaction penalty fee: $14.00
- No minimum balance requirement

**Rules:**
- Withdrawals exceeding 6 per month incur a $14 fee during monthly maintenance
- Negative withdrawal amounts are rejected
- Transaction counter tracks withdrawals only

**Example Usage:**
```java
SavingsAccount savings = new SavingsAccount(500.00);
savings.deposit(100.00);  // No limit on deposits
savings.withdraw(50.00);  // First withdrawal - no fee

// After 7 withdrawals in a month
savings.performMonthlyMaintenance(); // $14 fee applied
```

### Checking Account

**Features:**
- Unlimited deposits and withdrawals
- Minimum balance requirement: $100.00
- Monthly maintenance fee: $5.00
- Fee triggered by balance falling below minimum at any time

**Rules:**
- If balance drops below $100 at any point during the month, $5 fee is charged
- Fee is assessed during monthly maintenance
- Depositing money above $100 can cancel the fee for future months

**Example Usage:**
```java
CheckingAccount checking = new CheckingAccount(150.00);
checking.withdraw(60.00);  // Balance now $90 - triggers fee flag
checking.deposit(50.00);   // Balance now $140 - but fee still applies
checking.performMonthlyMaintenance(); // $5 fee still charged
```

## Core Functionality

### Account Creation

All accounts require a minimum opening deposit of $0.01:

```java
// Valid account creation
SavingsAccount account = new SavingsAccount(100.00);

// Invalid - throws IllegalArgumentException
SavingsAccount invalid = new SavingsAccount(0.005);
```

### Deposits

Deposits must be non-negative amounts:

```java
account.deposit(50.00);   // Valid
account.deposit(-10.00);  // Throws IllegalArgumentException
```

### Withdrawals

Withdrawals return boolean indicating success:

```java
boolean success = account.withdraw(25.00);  // Returns true if sufficient funds
boolean failed = account.withdraw(1000.00); // Returns false if insufficient funds
```

### Balance Inquiry

Current balance can be checked at any time:

```java
double currentBalance = account.getBalance();
System.out.println(account.toString()); // Formats as $XXX.XX
```

### Monthly Maintenance

Each account type handles monthly maintenance differently:

```java
account.performMonthlyMaintenance(); // Applies fees and resets counters
```

## Fee Structure Comparison

| Account Type | Monthly Fee | Trigger Condition | Additional Notes |
|--------------|-------------|-------------------|-----------------|
| Savings | $14.00 | More than 6 withdrawals | Applied only if limit exceeded |
| Checking | $5.00 | Balance below $100 anytime | Applied if minimum violated during month |

## Monthly Maintenance Examples

### Savings Account Scenarios

```java
// Scenario 1: Within withdrawal limit
SavingsAccount savings = new SavingsAccount(200.00);
for (int i = 0; i < 5; i++) {
    savings.withdraw(10.00);  // 5 withdrawals
}
savings.performMonthlyMaintenance(); // No fee - balance: $150.00

// Scenario 2: Exceeding withdrawal limit  
SavingsAccount savings2 = new SavingsAccount(200.00);
for (int i = 0; i < 8; i++) {
    savings2.withdraw(10.00); // 8 withdrawals
}
savings2.performMonthlyMaintenance(); // $14 fee - balance: $106.00
```

### Checking Account Scenarios

```java
// Scenario 1: Maintaining minimum balance
CheckingAccount checking = new CheckingAccount(150.00);
checking.withdraw(40.00); // Balance: $110 - above minimum
checking.performMonthlyMaintenance(); // No fee - balance: $110.00

// Scenario 2: Falling below minimum
CheckingAccount checking2 = new CheckingAccount(120.00);
checking2.withdraw(30.00); // Balance: $90 - below minimum
checking2.deposit(50.00);  // Balance: $140 - but flag already set
checking2.performMonthlyMaintenance(); // $5 fee - balance: $135.00
```

## Input Validation

The system includes comprehensive validation:

**Account Creation:**
- Starter amount must be >= $0.01
- Negative amounts throw `IllegalArgumentException`

**Deposits:**
- Amount must be non-negative
- Negative amounts throw `IllegalArgumentException`

**Withdrawals:**
- Negative amounts cause withdrawal to fail (return false)
- Amounts exceeding balance cause withdrawal to fail
- Successful withdrawals return true

## Testing

### Test Coverage

The project includes comprehensive unit tests covering:

**Account Base Class:**
- Constructor validation
- Deposit functionality
- Withdrawal success/failure cases
- Balance retrieval
- String formatting

**Savings Account:**
- Withdrawal transaction counting
- Monthly maintenance fee calculation
- Transaction limit enforcement

**Checking Account:**
- Minimum balance tracking
- Monthly maintenance fee assessment
- Balance threshold monitoring

### Running Tests

```bash
# Compile all source and test files
javac -cp ".:junit5.jar" src/bank/*.java test/bank/*.java

# Run all tests
java -cp ".:junit5.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## UML Class Diagram

```
┌─────────────────────┐
│     <<interface>>   │
│      IAccount       │
├─────────────────────┤
│ +deposit(double)    │
│ +withdraw(double):  │
│  boolean            │
│ +getBalance():double│
│ +performMonthly     │
│  Maintenance()      │
└─────────────────────┘
           ↑
           │ implements
           │
┌─────────────────────┐
│    <<abstract>>     │
│       Account       │
├─────────────────────┤
│ #balance: double    │
│ #transactionNum: int│
├─────────────────────┤
│ +Account(double)    │
│ +deposit(double)    │
│ +withdraw(double):  │
│  boolean            │
│ +getBalance():double│
│ +toString(): String │
│ +performMonthly     │
│  Maintenance()      │
└─────────────────────┘
           ↑
           │ extends
    ┌──────┴──────┐
    │             │
┌───▼──────┐ ┌───▼──────┐
│ Savings  │ │ Checking │
│ Account  │ │ Account  │
├──────────┤ ├──────────┤
│          │ │-belowMin │
│          │ │ Balance: │
│          │ │ boolean  │
├──────────┤ ├──────────┤
│+withdraw │ │+deposit  │
│+perform  │ │+withdraw │
│ Monthly  │ │+perform  │
│ Maintain │ │ Monthly  │
│ ance     │ │ Maintain │
│          │ │ ance     │
└──────────┘ └──────────┘
```

## Design Decisions

**Abstract Base Class Usage:**
- Common functionality implemented in `Account` class
- Reduces code duplication between account types
- Provides consistent interface implementation

**Transaction Tracking:**
- Base class tracks transaction numbers
- Each account type defines what constitutes a "transaction"
- Counters reset during monthly maintenance

**Fee Assessment Strategy:**
- Fees calculated and applied during monthly maintenance
- Different triggering conditions for each account type
- State tracking (flags) used to remember fee-triggering events

**Access Modifiers:**
- `protected` fields allow subclass access while maintaining encapsulation
- `private` constants define account-specific rules
- `public` interface methods provide external access

## Error Handling

**Constructor Validation:**
```java
try {
    Account account = new SavingsAccount(0.005); // Too small
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
```

**Deposit Validation:**
```java
try {
    account.deposit(-50.00); // Negative amount
} catch (IllegalArgumentException e) {
    System.out.println("Invalid deposit amount");
}
```

**Withdrawal Handling:**
```java
if (!account.withdraw(1000.00)) {
    System.out.println("Insufficient funds for withdrawal");
}
```

