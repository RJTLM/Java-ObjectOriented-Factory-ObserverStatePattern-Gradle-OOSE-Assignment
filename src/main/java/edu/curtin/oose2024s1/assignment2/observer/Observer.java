package edu.curtin.oose2024s1.assignment2.observer;

/**
Purpose:
    - Defines the contract for observer objects that need to be notified of changes.
Role:
    - Provides a method to update the observer, used by the observable to notify observers of changes.
 Reference:
 - Dewan, P. (2000). 15. Model-View-Controller (MVC) and Observer. University of North Carolina Computer Science at Chapel Hill. https://www.cs.unc.edu/~carterjl/teaching/notes/15_MVC_Notes.pdf - Used to help understand and implement observer pattern (only used for contextual based knowledge and further understanding of observer pattern).
*/
// Interface for observers.
public interface Observer {
    /**
    METHOD: update
    IMPORT: None
    EXPORT: None
    ALGORITHM:
    Called by the observable to notify the observer of changes.
    */
    void update();
}
