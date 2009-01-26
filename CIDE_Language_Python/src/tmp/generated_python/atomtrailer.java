package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atomtrailer extends GenASTNode {
  public atomtrailer(atom atom, ArrayList<atomtrailerEnd> atomtrailerEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<atom>("atom", atom),
      new PropertyZeroOrMore<atomtrailerEnd>("atomtrailerEnd", atomtrailerEnd)
    }, firstToken, lastToken);
  }
  public atomtrailer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atomtrailer(cloneProperties(),firstToken,lastToken);
  }
  public atom getAtom() {
    return ((PropertyOne<atom>)getProperty("atom")).getValue();
  }
  public ArrayList<atomtrailerEnd> getAtomtrailerEnd() {
    return ((PropertyZeroOrMore<atomtrailerEnd>)getProperty("atomtrailerEnd")).getValue();
  }
}
