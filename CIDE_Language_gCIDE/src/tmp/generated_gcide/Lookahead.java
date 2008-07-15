package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Lookahead extends GenASTNode {
  public Lookahead(LookaheadBody lookaheadBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LookaheadBody>("lookaheadBody", lookaheadBody)
    }, firstToken, lastToken);
  }
  public Lookahead(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Lookahead(cloneProperties(),firstToken,lastToken);
  }
  public LookaheadBody getLookaheadBody() {
    return ((PropertyOne<LookaheadBody>)getProperty("lookaheadBody")).getValue();
  }
}
