package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion extends GenASTNode {
  public expansion(local_lookahead local_lookahead, ArrayList<expansion_unit> expansion_unit, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<local_lookahead>("local_lookahead", local_lookahead),
      new PropertyOneOrMore<expansion_unit>("expansion_unit", expansion_unit)
    }, firstToken, lastToken);
  }
  public expansion(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion(cloneProperties(),firstToken,lastToken);
  }
  public local_lookahead getLocal_lookahead() {
    return ((PropertyZeroOrOne<local_lookahead>)getProperty("local_lookahead")).getValue();
  }
  public ArrayList<expansion_unit> getExpansion_unit() {
    return ((PropertyOneOrMore<expansion_unit>)getProperty("expansion_unit")).getValue();
  }
}
