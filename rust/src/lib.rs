// File: rust/src/lib.rs

// Common traits and types used across different implementations
#[derive(Debug, PartialEq, Copy, Clone)]
pub enum Exploration {
    Explored,
    UnExplored,
    LeftExplored, // Used in concurrent version
}

// Common test maze construction function with Clone trait bound added
pub fn create_test_maze_structure<F, T: Clone>(
    leaf_fn: F,
    branch_fn: impl Fn(&str, T, T) -> T,
) -> T
where
    F: Fn(&str) -> T,
{
    let leaf2 = leaf_fn("2");
    let leaf4 = leaf_fn("4");
    let leaf5 = leaf_fn("5");
    let leaf8 = leaf_fn("8");
    let branch3 = branch_fn("3", leaf4, leaf5.clone());
    let branch1 = branch_fn("1", leaf2, branch3.clone());
    let branch7 = branch_fn("7", leaf5, leaf8);
    let branch6 = branch_fn("6", branch3, branch7);
    branch_fn("0", branch1, branch6)
}

#[cfg(test)]
mod tests {
    use super::*;

    // Add some basic tests
    #[test]
    fn test_exploration_clone() {
        let exp = Exploration::UnExplored;
        let exp_clone = exp.clone();
        assert_eq!(exp, exp_clone);
    }
}