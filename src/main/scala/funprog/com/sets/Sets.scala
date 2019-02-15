package funprog.com.sets

object Sets {
  type Set = Int => Boolean

  def contains(set: Set, elem: Int ): Boolean = set(elem)

  def singletonSet(elem: Int): Set = x => x == elem

  def union(s: Set, t: Set): Set = x => contains(s, x) || contains(t, x)

  def intersect(s: Set, t: Set): Set = x => contains(s, x) && contains(t, x)

  def diff(s: Set, t: Set): Set = x => contains(s, x) && !contains(t, x)

  def filter(s: Set, p: Int => Boolean): Set = x => contains(s, x) && p(x)

  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (contains(s, a) && !p(a)) false
      else if (a > 1000) true
      else iter(a + 1)
    }
    iter(-1000)
  }

  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, k => !p(k))

  def map(s: Set, f: Int => Int): Set = x => if(exists(s, originalElem => f(originalElem) == x )) true else false
}
