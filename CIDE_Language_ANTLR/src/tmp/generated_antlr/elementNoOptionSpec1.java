package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class elementNoOptionSpec1 extends elementNoOptionSpec {
  public elementNoOptionSpec1(atom atom, ebnfSuffix ebnfSuffix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<atom>("atom", atom),
      new PropertyZeroOrOne<ebnfSuffix>("ebnfSuffix", ebnfSuffix)
    }, firstToken, lastToken);
  }
  public elementNoOptionSpec1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new elementNoOptionSpec1(cloneProperties(),firstToken,lastToken);
  }
  public atom getAtom() {
    return ((PropertyOne<atom>)getProperty("atom")).getValue();
  }
  public ebnfSuffix getEbnfSuffix() {
    return ((PropertyZeroOrOne<ebnfSuffix>)getProperty("ebnfSuffix")).getValue();
  }
}
