package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class term extends GenASTNode {
  public term(factor factor, ArrayList<termEnd> termEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<factor>("factor", factor),
      new PropertyZeroOrMore<termEnd>("termEnd", termEnd)
    }, firstToken, lastToken);
  }
  public term(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new term(cloneProperties(),firstToken,lastToken);
  }
  public factor getFactor() {
    return ((PropertyOne<factor>)getProperty("factor")).getValue();
  }
  public ArrayList<termEnd> getTermEnd() {
    return ((PropertyZeroOrMore<termEnd>)getProperty("termEnd")).getValue();
  }
}
