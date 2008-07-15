package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Prolog extends GenASTNode {
  public Prolog(XMLDecl xMLDecl, ArrayList<Misc> misc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<XMLDecl>("xMLDecl", xMLDecl),
      new PropertyZeroOrMore<Misc>("misc", misc)
    }, firstToken, lastToken);
  }
  public Prolog(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Prolog(cloneProperties(),firstToken,lastToken);
  }
  public XMLDecl getXMLDecl() {
    return ((PropertyOne<XMLDecl>)getProperty("xMLDecl")).getValue();
  }
  public ArrayList<Misc> getMisc() {
    return ((PropertyZeroOrMore<Misc>)getProperty("misc")).getValue();
  }
}
