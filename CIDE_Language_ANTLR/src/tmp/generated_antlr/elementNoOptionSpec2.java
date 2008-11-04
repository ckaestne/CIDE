package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class elementNoOptionSpec2 extends elementNoOptionSpec {
  public elementNoOptionSpec2(ebnf ebnf, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ebnf>("ebnf", ebnf)
    }, firstToken, lastToken);
  }
  public elementNoOptionSpec2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new elementNoOptionSpec2(cloneProperties(),firstToken,lastToken);
  }
  public ebnf getEbnf() {
    return ((PropertyOne<ebnf>)getProperty("ebnf")).getValue();
  }
}
