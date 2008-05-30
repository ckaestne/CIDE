package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Document extends GenASTNode implements ISourceFile {
  public Document(Prolog prolog, RootElement rootElement, ArrayList<Misc> misc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Prolog>("prolog", prolog),
      new PropertyOne<RootElement>("rootElement", rootElement),
      new PropertyZeroOrMore<Misc>("misc", misc)
    }, firstToken, lastToken);
  }
  public Document(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Document(cloneProperties(),firstToken,lastToken);
  }
  public Prolog getProlog() {
    return ((PropertyOne<Prolog>)getProperty("prolog")).getValue();
  }
  public RootElement getRootElement() {
    return ((PropertyOne<RootElement>)getProperty("rootElement")).getValue();
  }
  public ArrayList<Misc> getMisc() {
    return ((PropertyZeroOrMore<Misc>)getProperty("misc")).getValue();
  }
}
