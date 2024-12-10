# Experimental!
$ 2333133121414131402
Input     ←
Parse     ← ▽:⍜▽⋅¯1⇡2÷2°⊏-@0
Partition ← ↙:⍜⊏⋅⊃⇌⧻⊚¬⊃⊙∘▽⊸≥₀
Part₁     ← /+×°⊏ Partition Parse
Indicies  ↚ ⊙⟜(⊚=₁⊸⦷-₁⊚)⟜⧻⊚⊸⌕
Move      ↚ ⍜⊏(↻¯:)⊙:▽⊸≤⊃/↥⊂⊙: Indicies
Part₂     ← /+×°⊏×⊸≥₀∧Move⇌+₁⇡⊸/↥Parse
┌─╴Tests
  ⍤⤙≍2858&p⍜nowPart₂ Input
  ⍤⤙≍1928&p⍜nowPart₁ Input
└─╴

Idxs  ↚ ⟜(⊚=₁⊸⦷-₁⊚⧻)⊚⊸=
Move  ↚ ⍜⊏⇌⊂◡(⍤∞<∩⊢)Idxs
Part₂ ← /+×°⊏ ×⊸≥0∧⍣Move⋅∘ ⇌+₁⇡⊸⊣ Parse
