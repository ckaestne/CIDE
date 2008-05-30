package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_thead2 extends Element_thead {
  public Element_thead2(STag_thead sTag_thead, ArrayList<CMisc> cMisc1, ArrayList<Content_thead_Seq1> content_thead_Seq1, ETag_thead eTag_thead, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_thead>("sTag_thead", sTag_thead),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOneOrMore<Content_thead_Seq1>("content_thead_Seq1", content_thead_Seq1),
      new PropertyOne<ETag_thead>("eTag_thead", eTag_thead),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_thead2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_thead2(cloneProperties(),firstToken,lastToken);
  }
  public STag_thead getSTag_thead() {
    return ((PropertyOne<STag_thead>)getProperty("sTag_thead")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public ArrayList<Content_thead_Seq1> getContent_thead_Seq1() {
    return ((PropertyOneOrMore<Content_thead_Seq1>)getProperty("content_thead_Seq1")).getValue();
  }
  public ETag_thead getETag_thead() {
    return ((PropertyOne<ETag_thead>)getProperty("eTag_thead")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
