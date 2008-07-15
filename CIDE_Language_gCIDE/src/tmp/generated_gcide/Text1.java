package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Text1 extends Text {
  public Text1(Lookahead lookahead, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Lookahead>("lookahead", lookahead)
    }, firstToken, lastToken);
  }
  public Text1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Text1(cloneProperties(),firstToken,lastToken);
  }
  public Lookahead getLookahead() {
    return ((PropertyOne<Lookahead>)getProperty("lookahead")).getValue();
  }
}
