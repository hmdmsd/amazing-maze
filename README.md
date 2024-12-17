# Amazing Maze Project

## Overview
This project implements a maze exploration algorithm in both Scala and Rust, demonstrating different programming paradigms and approaches to concurrency. The maze is represented as a binary DAG (Directed Acyclic Graph) where each node is labeled with a string and can be either a branch (with left and right children) or a leaf.

## Project Structure
```
amazing-maze/
├── scala/                 # Scala implementation
│   └── src/
│       ├── main/
│       │   └── scala/
│       │       └── maze/
│       │           ├── MazeFunctional.scala    # Immutable version
│       │           └── MazeMutable.scala       # Mutable ListBuffer version
│       └── test/
│           └── scala/
│               └── maze/
│                   └── MazeTest.scala
├── rust/                  # Rust implementations
│   ├── src/
│   │   ├── bin/
│   │   │   ├── maze_basic.rs      # Basic version with references
│   │   │   ├── maze_rc.rs         # Version with Rc
│   │   │   └── maze_concurrent.rs  # Concurrent version
│   │   └── lib.rs
│   └── Cargo.toml
├── .gitignore
├── build.sbt
└── README.md
```

## Implementations

### Scala Versions

#### 1. Functional Implementation (MazeFunctional.scala)
- Uses immutable data structures
- Pure functional approach with pattern matching
- Returns a new list containing the exploration trace
- No side effects

#### 2. Mutable Implementation (MazeMutable.scala)
- Uses mutable ListBuffer for trace collection
- Maintains exploration state using mutable variables
- Side-effecting implementation
- More imperative style

### Rust Versions

#### 1. Basic Version (maze_basic.rs)
- Uses references with lifetimes
- Simple exploration without state
- Demonstrates basic Rust ownership concepts

#### 2. Rc Version (maze_rc.rs)
- Uses Rc (Reference Counting) smart pointers
- Implements shared ownership
- Uses RefCell for interior mutability
- Maintains exploration state

#### 3. Concurrent Version (maze_concurrent.rs)
- Uses Arc (Atomic Reference Counting) for thread-safe sharing
- Implements work-stealing pattern
- Uses Mutex for synchronization
- Parallel exploration with multiple threads

## Example Maze Structure
```
    0
   / \
  1   6
 / \ / \
2   3   7
   / \ / \
  4   5   8
```

## Setup and Running

### Initial Setup
1. Clone the repository:
```bash
git clone [repository-url]
cd amazing-maze
```

### Running Scala Version
1. Ensure you have SBT and Scala installed
2. From the project root, run:
```bash
sbt run
```

### Running Rust Version
1. Ensure you have Rust and Cargo installed
2. From the project root, run any of the following:
```bash
# Basic version
cargo run --bin maze_basic --manifest-path rust/Cargo.toml

# Rc version
cargo run --bin maze_rc --manifest-path rust/Cargo.toml

# Concurrent version
cargo run --bin maze_concurrent --manifest-path rust/Cargo.toml
```

## Testing

### Scala Tests
From the project root:
```bash
sbt test
```

### Rust Tests
From the project root:
```bash
cargo test --manifest-path rust/Cargo.toml
```

## Features
- Multiple implementation paradigms (functional, imperative, concurrent)
- Thread-safe concurrent exploration
- Comprehensive test suite
- Memory-safe implementations
- Efficient exploration algorithms

## Implementation Details

### Exploration Algorithm
1. When visiting an unexplored branch:
    - Mark it as explored
    - Record its label
    - Explore left subtree
    - Explore right subtree

2. When visiting an explored branch:
    - Only record its label
    - Skip further exploration

3. When visiting a leaf:
    - Record its label

### Concurrent Implementation
- Uses work-stealing pattern
- Multiple threads process nodes concurrently
- Thread-safe shared state management
- Local buffers to minimize contention

## Dependencies

### Scala
- Scala 3.3.1
- SBT (Scala Build Tool)
- JUnit for testing

### Rust
- Rust 2021 edition
- Standard library features:
    - Arc/Rc for reference counting
    - Mutex for synchronization
    - Thread for concurrency

## Performance Considerations
- Functional version: Memory efficient but creates new lists
- Mutable version: More memory efficient for large mazes
- Concurrent version: Best for large mazes, but has synchronization overhead

## Known Limitations
- No cycle detection (assumes DAG structure)
- Concurrent version may have non-deterministic exploration order
- Memory usage scales with maze size

## Authors
- Initial implementation based on work by Jacques Noyé (IMT Atlantique)
- Extended by Messaoud HAMDI & Mohamed Amine Dahech

## License
This project is licensed under the MIT License - see the LICENSE file for details