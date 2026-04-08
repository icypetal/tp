---
layout: page
title: Walkthrough
---

# Walkthrough: Setting Up the National Esports Team

Welcome to DraftDeck! This walkthrough will guide you through a realistic scenario where you're a team manager setting up a new esports team for an upcoming tournament. You'll learn how to use DraftDeck's key features to manage players, analyze data, and prepare your team for competition.

## Scenario Overview

You've just been hired as the team manager for the Singapore National Esports team, ahead of the upcoming Asian Games. Your first task is to:

1. Build a roster of 6 players (5 starters + 1 substitute)
2. Analyze player statistics and compare recruits
3. Draft a valid team composition
4. Record match data from practice games

Let's get started!

---

## Step 1: Launch DraftDeck and Start Fresh

First, launch the application and clear any existing sample data to start with a clean slate.

### Command:
```
clear
```

**Expected Output:**
All players will be removed from the list, and you'll see an empty player list.

---

## Step 2: Import the old roster.

Now let's import the old roster. 
First, close the application. Next, download data file containing them, found here.
Download the folder, unzip it, and overwrite the existing the data folder.

## Step 3: View Your Complete Roster

Now let's see all your players at once.

### Command:
```
list
```

**Expected Output:**
All 11 players are displayed in a numbered list, showing their name, phone, email, IGN, role, rank, and tags. You should see:

---

## Step 4: Add new Players

Between the last Asian Games and the current one, some new talent has emerged.
Specifically, a certain 'Koh Kai Jie' from team 'FaerieCharm'. His IGN is 'Dust', his rank is Challenger, and he plays the top lane.
Suppose his phone number is 93032101, and his email is kkj@gmail.com. Then, the command to add him will like as follows:

```
add n/Koh Kai Jie p/93032101 e/kkj@gmail.com i/Dust r/TOP rank/Challenger t/FaerieCharm
```

**Expected Output:**
Scrolling to the bottom of the list, we see that he has been added to the list.

However, he has no champions played yet.

## Step 5: Adding past matches

There are two ways we can go about adding past matches. We can either use the `result` command, which keeps track of dates, or the `stats` command.

For this case, we shall use the `stats` command.



## Step 6: Find Specific Players

Let's say you need to quickly find players with specific criteria.

### Find by Name
Search for players named "James."

**Command:**
```
find James
```

**Expected Output:**
Only James Wilson is displayed (index 3 in the original list).

### Filter by Role
View all players tagged as "starter."

**Command:**
```
filter t/starter
```

**Expected Output:**
Displays Marcus Chen, Sarah Kim, James Wilson, Elena Rodriguez, and David Tan (all 5 starters).

### Filter by Multiple Criteria
Find all PLATINUM-ranked players who play JUNGLE or MID.

**Command:**
```
filter r/jungle r/mid rank/platinum
```

**Expected Output:**
Displays Sarah Kim and Alex Turner (both PLATINUM rank with JUNGLE or MID roles).

---

## Step 7: Compare Two Players

You're considering whether to start James Wilson or Alex Turner for an upcoming match. Let's compare them.

### Command:
```
compare i/CaptainMid i/FlexSub
```

**Expected Output:**
A side-by-side comparison showing:
- **CaptainMid (James Wilson):** MID, MASTER, starter, captain
- **FlexSub (Alex Turner):** MID, PLATINUM, substitute

This helps you make an informed decision - James has higher rank and captain experience.

---

## Step 8: Draft a Valid Team Composition

Now let's practice drafting a valid 5-player team. A valid team needs exactly one player for each role.

### Command:
```
draft 1 2 3 4 5
```

**Expected Output:**
```
✓ Valid team composition!
TOP: PhoenixTop (Marcus Chen)
JUNGLE: WildJungle (Sarah Kim)
MID: CaptainMid (James Wilson)
BOT: StrikeADC (Elena Rodriguez)
SUPPORT: GuardianSupport (David Tan)
```

### Try an Invalid Composition
Let's see what happens if we try to draft an invalid team (missing a role).

**Command:**
```
draft 1 2 3 4 6
```

**Expected Output:**
An error message indicating the team composition is invalid, likely because you're missing a SUPPORT player (index 6 is Alex Turner, a MID substitute).

---

## Step 9: Record Match Statistics

After a practice match, you need to update player statistics. Let's record that Marcus Chen performed exceptionally well on Ahri.

### Command:
```
stats 1 ent/Ahri k/15 d/3 a/8
```

**Expected Output:**
A confirmation message indicating that Marcus Chen's statistics for Ahri have been updated:
- Kills: 15
- Deaths: 3
- Assists: 8

### Update Another Player
Let's also update Elena Rodriguez's performance on Jinx.

**Command:**
```
stats 4 ent/Jinx k/12 d/5 a/10
```

**Expected Output:**
Confirmation that Elena's Jinx statistics have been updated.

---

## Step 10: Record Match Results

Your team just won their first practice match! Let's record the result.

### Command:
```
result w/WIN i/PhoenixTop ent/Ahri k/15 d/3 a/8 i/WildJungle ent/LeeSin k/8 d/4 a/12 i/CaptainMid ent/Yasuo k/10 d/5 a/7 i/StrikeADC ent/Jinx k/12 d/5 a/10 i/GuardianSupport ent/Leona k/1 d/2 a/15
```

**Expected Output:**
A confirmation message showing:
- Match Result: WIN
- Date: Today's date
- Player statistics recorded for all 5 players:
  - PhoenixTop: Ahri (15/3/8)
  - WildJungle: LeeSin (8/4/12)
  - CaptainMid: Yasuo (10/5/7)
  - StrikeADC: Jinx (12/5/10)
  - GuardianSupport: Leona (1/2/15)

---

## Step 11: Player Management

### Editing Player Information
David Tan has a new phone number.

**Command:**
```
edit 5 p/98765432
```

**Expected Output:**
Confirmation that David Tan's phone number has been updated.

### Removing a Player
If you need to remove a player from your roster:

**Command:**
```
delete 6
```

**Expected Output:**
Alex Turner (the substitute) is removed from the roster. The remaining players are renumbered accordingly.

---

## Step 12: Get Help

At any time, if you forget how to use a command:

**Command:**
```
help
```

**Expected Output:**
A help window opens showing all available commands and their formats.

---

## Summary

Congratulations! You've successfully:

✓ Built a complete 5-player team roster  
✓ Viewed and searched through your players  
✓ Compared players to make roster decisions  
✓ Drafted a valid team composition  
✓ Recorded player statistics  
✓ Documented match results  

Your team is now set up and ready for the tournament! You can continue to use DraftDeck to:

- Add new players as your team grows
- Track player statistics over time
- Record all match results
- Analyze team performance with the `filter` and `compare` commands
- Draft different team compositions for different strategies

## Next Steps

- Explore the [User Guide](UserGuide.html) for detailed command reference
- Check out the [FAQ](UserGuide.html#faq) for common questions
- Start using DraftDeck for real team management!

---

<div markdown="span" class="alert alert-primary">:bulb: **Pro Tip:**
All your data is automatically saved after every command. No need to manually save - your team roster and match records are safe!
</div>