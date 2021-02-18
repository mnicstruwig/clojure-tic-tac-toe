# Tic-tac-toe in Clojure

A (very) rudimentary version of tic-tac-toe, written in Clojure.

I wrote this as my first-ever learning project to become familiar with Clojure,
in order to practice some of the concepts covered in the wonderful [Clojure for
the Brave and True](https://www.braveclojure.com/). 

Expect bugs, anti-patterns (recursion still hurts my head often), etc. I think
it'll be fun to re-implement the game of tic-tac-toe after a year or so of
casual Clojure use to compare differences in implementation.

## Usage
Assuming you have `clojure` and `leiningen` installed, simply clone the
repository and execute:

``` sh
lein run
```

Or, build the project with:

``` sh
lein uberjar
```

and execute with:
``` sh
java -jar target/uberjar/tictactoe-0.1.0-standalone.jar
```

You'll be presented with a tic-tac-toe board, and can proceed to play by
entering indexes for each player: 

``` sh
1 | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9

X 's move
Which index?

```

Have fun.

## License

Copyright © 2021 Michael Struwig.

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
