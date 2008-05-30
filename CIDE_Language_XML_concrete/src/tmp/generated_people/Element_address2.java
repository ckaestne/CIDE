package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_address2 extends Element_address {
  public Element_address2(STag_address sTag_address, ArrayList<CMisc> cMisc1, ArrayList<Content_address_Seq1> content_address_Seq1, ETag_address eTag_address, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_address>("sTag_address", sTag_address),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyZeroOrMore<Content_address_Seq1>("content_address_Seq1", content_address_Seq1),
      new PropertyOne<ETag_address>("eTag_address", eTag_address),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_address2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_address2(cloneProperties(),firstToken,lastToken);
  }
  public STag_address getSTag_address() {
    return ((PropertyOne<STag_address>)getProperty("sTag_address")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_address_Seq1> getContent_address_Seq1() {
    return ((PropertyZeroOrMore<Content_address_Seq1>)getProperty("content_address_Seq1")).getValue();
  }
  public ETag_address getETag_address() {
    return ((PropertyOne<ETag_address>)getProperty("eTag_address")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
