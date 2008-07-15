package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Production extends GenASTNode {
  public Production(Lookahead lookahead, Rewrite rewrite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Lookahead>("lookahead", lookahead),
      new PropertyOne<Rewrite>("rewrite", rewrite)
    }, firstToken, lastToken);
  }
  public Production(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Production(cloneProperties(),firstToken,lastToken);
  }
  public Lookahead getLookahead() {
    return ((PropertyZeroOrOne<Lookahead>)getProperty("lookahead")).getValue();
  }
  public Rewrite getRewrite() {
    return ((PropertyOne<Rewrite>)getProperty("rewrite")).getValue();
  }
}
