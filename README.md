gmail-like checkable view animation
===================================

#### Aim

- https://medium.com/@ataulm/implementing-the-gmail-for-android-selected-email-animation-fe34771fc75a

#### Current state (inc. next steps if applicable)

- ~~got a swivel~~
- ~~got an interpolator~~
- added a fake demo for email
- trying with ViewFlipper and a backwards checkmark so we can just use one animation for the whole thing
- use matrix scale to avoid the reversed image
- animate in both directions
- verify it works with pre-selected emails (config change on rotate)

TODO:

- spammy clicks
