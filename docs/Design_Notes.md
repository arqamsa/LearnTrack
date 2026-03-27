# Design Notes

## Why ArrayList Instead of Array?

Arrays have a fixed size set at creation time. Since the number of students and courses
is not known upfront, using `Student[] students = new Student[100]` would either waste
memory or crash when full.

`ArrayList` grows automatically as items are added, requires no manual resizing, and
provides clean methods like `add()`, `size()`, and iteration with for-each — making it
the right choice for dynamic in-memory data.

---

## Where Static Members Were Used and Why

**`IdGenerator`** uses `static` fields (`studentIdCounter`, etc.) and `static` methods
(`getNextStudentId()`, etc.) because ID counters are application-wide shared state. There
is only one counter per entity type across the whole app — not one per object. Static
ensures every call to `getNextStudentId()` reads and increments the same counter,
regardless of who calls it.

---

## Where Inheritance Was Used and What Was Gained

`Student` and `Trainer` both share `id`, `firstName`, `lastName`, and `email`. Without
inheritance, these four fields and their getters/setters would be duplicated in both
classes.

By introducing the abstract `Person` base class, the shared fields are written once.
Both subclasses use `super(...)` in their constructors to initialise them. Any future
change to common fields only needs to happen in one place.

The `getDisplayName()` override in each subclass demonstrates polymorphism — the same
method call returns different, contextually appropriate output depending on the actual
object type, without any `instanceof` checks in the calling code.
