$ 47|53
$ 97|13
$ 97|61
$ 97|47
$ 75|29
$ 61|13
$ 75|53
$ 29|13
$ 97|29
$ 53|29
$ 61|53
$ 97|53
$ 61|29
$ 47|13
$ 75|47
$ 97|75
$ 47|61
$ 75|61
$ 47|29
$ 75|13
$ 53|13
$ 
$ 75,47,61,53,29
$ 97,61,53,29,13
$ 75,29,13
$ 75,97,47,61,53
$ 61,13,29
$ 97,13,75,29,47
Input ←
Parse ← (
  °□₂⊜(□⊜□⊸≠@\n)¬⊸⦷"\n\n"
  ⊓≡◇(⊜⋕⊸≠@|)⍚(⊜⋕⊸≠@,))
ValidRows ← ≡°□⍚(/↧≡/>▽¬≡/↥⊸⌕⧻⤙⊸⍜∩♭⊗:):¤
SumMiddle ← ≡◇(⊡⌊÷₂⊸⧻)
SortRow ← ⍚(
  ▽¬≡/↥⊸⌕⧻⤙⊸⍜∩♭⊗:
  ⊙°⊏⍜≡⇌⍆▽¬⊸≡/>
  ⋅⊏⍥(:⍜▽⇌⊸∊⊙:°⊂)⊸⧻
)
┌─╴Tests
  ⍤⤙≍ 143 /+SumMiddle ×ValidRows Parse Input
  ⍤⤙≍ 123 /+SumMiddle SortRow ⊙¤▽¬⤚ValidRows Parse Input
└─╴
# Updates Rules ? Input
Parse ← (
  :°□₂⊜(□⊜□⊸≠@\n)¬⊸⦷"\n\n"
  ⊓⍚(⊜⋕⊸≠@,)(⍉≡◇(⊜⋕⊸≠@|))
)
IsCorrect     ← /×/≥≡⬚W⊗:¤
GetDependents ← ∩◴⟜⊂⊙◌◡(▽∊⊙⋅°⊟)
Dependencies  ← ⧻⋅⊙◌⍥GetDependents∞ ⊙[]
TopoSort ← (
  ⟜(¤≡▽¤/×⊸∊)      # Remove unused rules (important!)
  ⊏⍖⤚≡Dependencies # Count dependencies then sort
)
Part₁ ← /+×≡◇⊃(⊡⌊÷₂⊸⧻|IsCorrect) ⊙¤ Parse
Part₂ ← /+≡◇(⊡⌊÷₂⊸⧻TopoSort)▽¬◡≡◇IsCorrect ⊙¤ Parse

┌─╴Tests₂
  ⍤⤙≍ 143 Part₁ Input
  ⍤⤙≍ 123 Part₂ Input
└─╴

FilterRules ← ⟜(≡▽¤/×⊸∊) # Remove rules which are not applicable
Middles     ← ≡◇(⊡⌊÷₂⊸⧻) # Get the middle value
SortByRules ← ⊏⤚(⍖∊:⊙⍉⊞⊟.) # Sort by the rules.
# Apply sorting rules to input sequences
# Returns an array with two values
# The sum of the middle value where input was sorted
# The sum of the middle values where input was not sorted
# 43 characters.
Solve ← ⊕/+⊃=Middles ⤚⍚(SortByRules FilterRules)⊙¤

┌─╴Tests₃
  ⍤⤙≍ 123_143 Solve Parse Input
└─╴