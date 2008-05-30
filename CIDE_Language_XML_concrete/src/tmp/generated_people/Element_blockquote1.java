package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_blockquote1 extends Element_blockquote {
  public Element_blockquote1(EmptyTag_blockquote emptyTag_blockquote, ArrayList<CMisc> cMisc, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyTag_blockquote>("emptyTag_blockquote", emptyTag_blockquote),
      new PropertyZeroOrMore<CMisc>("cMisc", cMisc)
    }, firstToken, lastToken);
  }
  public Element_blockquote1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_blockquote1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyTag_blockquote getEmptyTag_blockquote() {
    return ((PropertyOne<EmptyTag_blockquote>)getProperty("emptyTag_blockquote")).getValue();
  }
  public ArrayList<CMisc> getCMisc() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc")).getValue();
  }
}
