package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element_table2 extends Element_table {
  public Element_table2(STag_table sTag_table, ArrayList<CMisc> cMisc1, Content_table_Seq1 content_table_Seq1, ETag_table eTag_table, ArrayList<CMisc> cMisc2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<STag_table>("sTag_table", sTag_table),
      new PropertyZeroOrMore<CMisc>("cMisc1", cMisc1),
      new PropertyOne<Content_table_Seq1>("content_table_Seq1", content_table_Seq1),
      new PropertyOne<ETag_table>("eTag_table", eTag_table),
      new PropertyZeroOrMore<CMisc>("cMisc2", cMisc2)
    }, firstToken, lastToken);
  }
  public Element_table2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Element_table2(cloneProperties(),firstToken,lastToken);
  }
  public STag_table getSTag_table() {
    return ((PropertyOne<STag_table>)getProperty("sTag_table")).getValue();
  }
  public ArrayList<CMisc> getCMisc1() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc1")).getValue();
  }
  public Content_table_Seq1 getContent_table_Seq1() {
    return ((PropertyOne<Content_table_Seq1>)getProperty("content_table_Seq1")).getValue();
  }
  public ETag_table getETag_table() {
    return ((PropertyOne<ETag_table>)getProperty("eTag_table")).getValue();
  }
  public ArrayList<CMisc> getCMisc2() {
    return ((PropertyZeroOrMore<CMisc>)getProperty("cMisc2")).getValue();
  }
}
