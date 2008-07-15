package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionalNode extends GenASTNode {
  public OptionalNode(Lookahead lookahead, Terminal terminal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Lookahead>("lookahead", lookahead),
      new PropertyOne<Terminal>("terminal", terminal)
    }, firstToken, lastToken);
  }
  public OptionalNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionalNode(cloneProperties(),firstToken,lastToken);
  }
  public Lookahead getLookahead() {
    return ((PropertyZeroOrOne<Lookahead>)getProperty("lookahead")).getValue();
  }
  public Terminal getTerminal() {
    return ((PropertyOne<Terminal>)getProperty("terminal")).getValue();
  }
}
