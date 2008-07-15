package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Document extends GenASTNode implements ISourceFile {
  public Document(Prolog prolog, Element element, ArrayList<Misc> misc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Prolog>("prolog", prolog),
      new PropertyOne<Element>("element", element),
      new PropertyZeroOrMore<Misc>("misc", misc)
    }, firstToken, lastToken);
  }
  public Document(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Document(cloneProperties(),firstToken,lastToken);
  }
  public Prolog getProlog() {
    return ((PropertyOne<Prolog>)getProperty("prolog")).getValue();
  }
  public Element getElement() {
    return ((PropertyOne<Element>)getProperty("element")).getValue();
  }
  public ArrayList<Misc> getMisc() {
    return ((PropertyZeroOrMore<Misc>)getProperty("misc")).getValue();
  }
}
