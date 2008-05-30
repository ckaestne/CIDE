package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_name2 extends Element_name {
  public Element_name2(STag_name sTag_name, ArrayList<CMisc> cMisc1, Content_name_Seq1 content_name_Seq1, ETag_name eTag_name, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_name>("sTag_name", sTag_name),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_name_Seq1>("content_name_Seq1", content_name_Seq1),
      new PropertyOne<ETag_name>("eTag_name", eTag_name),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_name2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_name2(cloneProperties(),firstToken,lastToken);
  }
  public STag_name getSTag_name() {
    return ((PropertyOne<STag_name>)getProperty("sTag_name")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_name_Seq1 getContent_name_Seq1() {
    return ((PropertyOne<Content_name_Seq1>)getProperty("content_name_Seq1")).getValue();
  }
  public ETag_name getETag_name() {
    return ((PropertyOne<ETag_name>)getProperty("eTag_name")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
