package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element2 extends Element {
  public Element2(STag sTag, ArrayList<Content> content, ETag eTag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag>("sTag", sTag),
      new PropertyZeroOrMore<Content>("content", content),
      new PropertyOne<ETag>("eTag", eTag)
    }, firstToken, lastToken);
  }
  public Element2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element2(cloneProperties(),firstToken,lastToken);
  }
  public STag getSTag() {
    return ((PropertyOne<STag>)getProperty("sTag")).getValue();
  }
  public ArrayList<Content> getContent() {
    return ((PropertyZeroOrMore<Content>)getProperty("content")).getValue();
  }
  public ETag getETag() {
    return ((PropertyOne<ETag>)getProperty("eTag")).getValue();
  }
}
